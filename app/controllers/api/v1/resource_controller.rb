module Api
  module V1
    class ResourceController < ApplicationController
      before_action :set_default_response_format
      before_action :set_resource, only: [:destroy, :show, :update]

      def index
        plural_resource_name = "@#{resource_name.pluralize}"
        resources = class_of_resource.where(query_params)

        instance_variable_set(plural_resource_name, resources)
        render json: instance_variable_get(plural_resource_name)
      end

      def create
        if set_resource(class_of_resource.new(resource_params))
          get_resource.save
          render :create
        else
          render json: get_resource.errors, status: :unprocessable_entity
        end
      end

      def show
        if !class_of_resource.where(:id => params[:id]).empty?
          render :show
        else
          render status: 404, json: {
            status: "not_found"
          }
        end
      end

      def update
        if get_resource.update(resource_params)
          render :update
        else
          render json: get_resource.errors, status: :unprocessable_entity
        end
      end

      def destroy
        if get_resource.destroy
          head :no_content
        else
          render json: get_resource.errors, status: :unprocessable_entity
        end
      end

      private
        def get_resource
          instance_variable_get("@#{resource_name}")
        end

        def query_params
          {}
        end

        def class_of_resource
          @class_of_resource ||= resource_name.classify.constantize
        end

        def resource_name
          @resource_name ||= self.controller_name.singularize
        end

        def resource_params
          @resource_params ||= self.send("#{resource_name}_params")
        end

        def set_resource(resource = nil)
          resource ||= class_of_resource.where(id: params[:id]).first
          instance_variable_set("@#{resource_name}", resource)
        end

        def set_default_response_format
          request.format = :json
        end

    end

  end

end

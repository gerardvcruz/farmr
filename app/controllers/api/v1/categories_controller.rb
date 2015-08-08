module Api
  module V1
    class CategoriesController < ResourceController
      def show
        if !class_of_resource.where(:id => params[:id]).empty?
          if params[:include].include? "suppliers"
            @includes[:suppliers] = []
            User.all.each do |user|
              if user.supplies.any? { |supply| supply.category_id == params[:id].to_i }
                @includes[:suppliers] << user
              end
            end
          end

          render :show
        else
          render status: 404, json: {
            status: "not_found"
          }
        end
      end
    end
  end
end

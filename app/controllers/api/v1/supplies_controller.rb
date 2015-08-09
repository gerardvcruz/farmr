module Api
  module V1
    class SuppliesController < ResourceController

      private
        def supply_params
          params.require(:supply).permit!
        end
      end
  end
end

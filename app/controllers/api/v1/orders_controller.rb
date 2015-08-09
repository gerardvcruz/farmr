module Api
  module V1
    class OrdersController < ResourceController

      private
        def orders_params
          params.require(:order).permit!
        end
      end
    end
  end
end

Rails.application.routes.draw do
  mount_devise_token_auth_for 'User', at: 'auth'

  namespace :api do
    namespace :v1 do
      resources :supplies do

      end

      resources :logs do

      end

      resources :categories, only: [:index] do

      end

      resources :units, only: [:index] do

      end

      resources :orders do

      end
    end
  end
end

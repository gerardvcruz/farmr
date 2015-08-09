Rails.application.routes.draw do
  mount_devise_token_auth_for 'User', at: 'auth', controllers: {
    sessions: 'auth/sessions',
    registrations: 'auth/registrations'
  }

  namespace :api do
    namespace :v1 do
      resources :supplies do
        # custome supply routes
      end

      resources :logs do
        # custom log routes
      end

      resources :categories, only: [:index, :show] do
        # custom category routes
      end

      resources :units, only: [:index] do
        # custom unit routes
      end

      resources :orders, only: [:create] do
        # custom order routes
      end

      resources :users, only: [:index, :show] do
        # custom user routes
      end
    end
  end
end

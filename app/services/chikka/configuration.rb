module Chikka
  class Configuration

    attr_accessor :client_id, :secret_key, :short_code, :api_url

    def initialize
      @client_id  = Rails.application.config.chikka_client_id
      @secret_key = Rails.application.config.chikka_secret_key
      @short_code = Rails.application.config.chikka_short_code
      @api_url    = "https://post.chikka.com"
    end

  end

end

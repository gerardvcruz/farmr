module Chikka
  class Object
    attr_accessor :message_type, :mobile_number, :message_id, :message

    def initialize params
      @message_type  = params[:message_type]
      @mobile_number = params[:mobile_number]
      @message_id    = params[:message_id]
      @message       = params[:message]
    end

    def send_text
      conn = Faraday.new(:url => Chikka.configuration.api_url) do |faraday|
        faraday.request  :url_encoded
        faraday.response :logger
        faraday.adapter  Faraday.default_adapter
      end

      response =
        conn.post do |req|
          req.url '/smsapi/request'
          req.body = {
            :message_type => @message_type.to_s,
            :mobile_number => @mobile_number,
            :shortcode => Chikka.configuration.short_code,
            :message_id => @message_id.to_s,
            :message => @message.to_s,
            :client_id => Chikka.configuration.client_id,
            :secret_key => Chikka.configuration.secret_key
           }
        end

      p response
    end
  end
end

require 'test_helper'

class ChikkaTest < ActiveSupport::TestCase
  test "configuration" do
    Chikka.configure do |config|
      config.client_id = Rails.application.config.chikka_client_id
      config.secret_key = Rails.application.config.chikka_secret_key
      config.short_code = Rails.application.config.chikka_short_code
    end

    assert Chikka.configuration
  end

  test "send_text" do
    Chikka.configure do |config|
      config.client_id = Rails.application.config.chikka_client_id
      config.secret_key = Rails.application.config.chikka_secret_key
      config.short_code = Rails.application.config.chikka_short_code
    end

    @message = Chikka::Object.new({
      message_type: "SEND",
      mobile_number: 639166200691,
      message_id: "abc1234",
      message: "Test Chikka service from Farmr"
    })

    assert @message.send_text
  end
end

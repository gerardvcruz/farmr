require 'test_helper'

class ChikkaTest < ActiveSupport::TestCase
  test "configuration" do
    Chikka.configure do |config|
      config.client_id = "cf06875b905f7483fe596a2f0c125e960902403e52bfc1ad80321b6de10b9dfc"
      config.secret_key = "c1a23593e54123d5e374adad26c9ec31e6edd3d0b90a3f657b647f9ce5b0d6fd"
      config.short_code = 2929032767
    end

    assert Chikka.configuration
  end

  test "send_text" do
    Chikka.configure do |config|
      config.client_id = "cf06875b905f7483fe596a2f0c125e960902403e52bfc1ad80321b6de10b9dfc"
      config.secret_key = "c1a23593e54123d5e374adad26c9ec31e6edd3d0b90a3f657b647f9ce5b0d6fd"
      config.short_code = 2929032767
    end

    @message = Chikka::Object.new({
      message_type: "SEND",
      mobile_number: "09166200691",
      message_id: "abc1234",
      message: "Test Chikka service from Farmr"
    })

    assert @message.send_text
  end
end

module Chikka
  autoload :Configuration, 'chikka/configuration'
  autoload :Object, 'chikka/object'

  class << self
    attr_accessor :configuration
  end

  def self.configuration
    @configuration ||= Configuration.new
  end

  def self.configure
    yield(configuration)
  end
end

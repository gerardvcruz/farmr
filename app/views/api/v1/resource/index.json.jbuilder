if !@resources.nil?
  @data = []

  @resources.each do |resource|
    @data << resource
  end

  json.set! @resources.model_name.to_s.pluralize.downcase, @data
end


if !@resources.nil?
  @data = []

  @resources.each do |resource|
    @resource = resource.attributes
    @resource[:category_name] = Category.find(resource[:category_id]).name
    @data << @resource
  end

  json.set! @resources.model_name.to_s.pluralize.downcase, @data
end


if @category != nil
  json.id   @category.id
  json.name @category.name

  unless @includes.empty?
    @includes.each do |key, value|
      json.set! key.to_sym, value
    end
  end
end

if @user != nil
  json.uid       @user.id
  json.name      @user.name
  json.email     @user.email
  json.address   @user.address
  json.user_type @user.user_type

  unless @includes.empty?
    @includes.each do |key, value|
      json.set! key.to_sym, value
    end
  end
end

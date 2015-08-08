if @supply != nil
  json.id       @supply.id
  json.supplier User.find(@supply.supplier_id)
  json.category @supply.category.name
  json.quantity @supply.quantity
  json.unit     @supply.unit.name
end

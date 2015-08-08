class AddPriceToSupply < ActiveRecord::Migration
  def change
    add_column :supplies, :price, :decimal
  end
end

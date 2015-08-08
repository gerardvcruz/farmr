class CreateOrders < ActiveRecord::Migration
  def change
    create_table :orders do |t|
      t.integer :supplier_id
      t.integer :buyer_id
      t.integer :supply_id
      t.integer :quantity
      t.decimal :total

      t.timestamps null: false
    end
  end
end

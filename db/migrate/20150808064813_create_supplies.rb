class CreateSupplies < ActiveRecord::Migration
  def change
    create_table :supplies do |t|
      t.integer :supplier_id
      t.integer :category_id
      t.integer :quantity
      t.integer :unit_id

      t.timestamps null: false
    end
  end
end

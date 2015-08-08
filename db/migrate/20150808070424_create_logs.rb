class CreateLogs < ActiveRecord::Migration
  def change
    create_table :logs do |t|
      t.integer :activity_type
      t.integer :supplier_id
      t.integer :buyer_id

      t.timestamps null: false
    end
  end
end

class CreateUsers < ActiveRecord::Migration
  def change
    create_table :users do |t|
      t.string :name
      t.text :address
      t.string :contact, array: true, default: []
      t.integer :type
      t.string :username
      t.string :password

      t.timestamps null: false
    end
  end
end

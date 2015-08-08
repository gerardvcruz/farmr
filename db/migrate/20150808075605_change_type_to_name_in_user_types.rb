class ChangeTypeToNameInUserTypes < ActiveRecord::Migration
  def change
    remove_column :user_types, :type
    add_column :user_types, :name, :string
  end
end

class User < ActiveRecord::Base
  # Include default devise modules.
  devise :database_authenticatable, :registerable,
          :recoverable, :rememberable, :trackable, :validatable,
          :omniauthable
  include DeviseTokenAuth::Concerns::User

  has_many :supplies, foreign_key: "supplier_id"
  has_many :categories, through: :supplies
  has_many :buyer_orders, class_name: "Order", foreign_key: "buyer_id"
  has_many :supplier_orders, class_name: "Order", foreign_key: "supplier_id"
end

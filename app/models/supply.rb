class Supply < ActiveRecord::Base
  belongs_to :user
  belongs_to :category
  belongs_to :unit
end

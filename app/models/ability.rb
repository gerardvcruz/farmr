class Ability
  include CanCan::Ability

  def initialize(user)
    user ||= User.new
    user_type = user.user_type

    if user_type.name == "Supplier"
      can :manage, Supply
    elsif user_type.name == "Buyer"

    else
      cannot :manage, :all
    end
  end
end

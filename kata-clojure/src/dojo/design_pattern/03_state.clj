(ns dojo.design-pattern.03-state)

(def db-news-feeds (take 1000 (range)))

(defmulti news-feed :user-state)

(defmethod news-feed :subscription [user]
  db-news-feeds)

(defmethod news-feed :no-subcription [user]
  (take 10 db-news-feeds))

(def user (atom {:name "Jackie Brown"
                 :balance 0
                 :user-state :no-subcription}))

(def ^:const SUBSCRIPTION_COST 10)

(defn pay [user amount]
  (swap! user update-in [:balance] + amount)
  (when (and (>= (:balance @user) SUBSCRIPTION_COST)
             (= :no-subcription (:user-state @user)))
    (swap! user assoc :user-state :subscription)
    (swap! user update-in [:balance] - SUBSCRIPTION_COST)))

;; (news-feed @user)

;; (pay user 10)

;; (news-feed @user)

;; (pay user 25)

;; (news-feed @user)

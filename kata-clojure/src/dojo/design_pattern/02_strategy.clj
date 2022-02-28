(ns dojo.design-pattern.02-strategy)

(def users [
            {:subscription false :name "bob"}
            {:subscription false :name "anne"}
            {:subscription true :name "tom"}
            {:subscription true :name "david"}
            ])

(sort (comparator
       (fn [u1 u2]
         (cond
           (= (:subscription u1) (:subscription u2)) (neg? (compare (:name u1) (:name u2)))
           (:subscription u1) true
           :else false)))
      users)

;; forward sort
(sort-by (juxt (complement :subscription) :name) users)

;; reverse sort
(sort-by (juxt :subscription :name) #(compare %2 %1) users)

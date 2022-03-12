(ns dojo.design-pattern.08-prototype)

(def registration-prototype
  {:name          "Zed"
   :email         "zzzed@gmail.com"
   :date-of-birth "1970-01-01"
   :weight        60
   :gender        :male
   :status        :single
   :children      [{:gender :female}]
   :month-salary  1000
   :brands        ["Adidas" "GAP"]})

;; return new object
(assoc registration-prototype
       :name "Mia Vallace"
       :email "tomato@gmail.com"
       :weight 52
       :gender :female
       :month-salary 0)

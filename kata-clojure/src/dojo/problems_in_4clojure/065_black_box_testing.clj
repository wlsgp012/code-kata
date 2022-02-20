(ns dojo.problems-in-4clojure.065-black-box-testing)
;; https://4clojure.oxal.org/#/problem/65

(defn sol
  [xs])

(= :map (sol {:a 1, :b 2}))
(= :list (sol (range (rand-int 20))))
(= :vector (sol [1 2 3 4 5 6]))
(= :set (sol #{10 (rand-int 5)}))
(= [:map :set :vector :list] (map sol [{} #{} [] ()]))


;; others
#({\# :set \[ :vector \{ :map \( :list \c :list} (first (str %)))

(fn [obj]
  (cond (= (conj obj obj) obj)
        :map
        (= (conj obj obj) (conj (conj obj obj) obj))
        :set
        (= (first (conj (conj obj true) false)) false)
        :list
        :else
        :vector))

(fn [s]
  (if (associative? s)
    (if (= (first (assoc s 0 :test)) :test) :vector :map)
    (if (= (first (conj s 0 :test)) :test) :list :set)))

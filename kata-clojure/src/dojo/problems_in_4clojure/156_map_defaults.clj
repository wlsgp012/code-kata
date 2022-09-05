(ns dojo.problems-in-4clojure.156-map-defaults)

(defn sol
  [default s]
  (reduce #(conj %1 [%2 default]) {} s))

(= (sol 0 [:a :b :c]) {:a 0 :b 0 :c 0})

(= (sol "x" [1 2 3]) {1 "x" 2 "x" 3 "x"})

(= (sol [:a :b] [:foo :bar]) {:foo [:a :b] :bar [:a :b]})

;; others

(fn [default s] (reduce #(assoc %1 %2 default) {} s))

#(apply hash-map (interleave %2 (repeat %)))

(fn [default s]
  (zipmap s (repeat default)))

#(into {}
       (for [x %2] {x %1}))

(ns dojo.problems-in-4clojure.061-map-construction)
;; https://4clojure.oxal.org/#/problem/61

(defn sol
  [ks vs]
  (into {} (map #(vector %1 %2) ks vs)))

(= (sol [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3})
(= (sol [1 2 3 4] ["one" "two" "three"]) {1 "one", 2 "two", 3 "three"})
(= (sol [:foo :bar] ["foo" "bar" "baz"]) {:foo "foo", :bar "bar"})

;; others
#(apply hash-map (interleave %1 %2))

#(apply hash-map (mapcat list % %2))

#(into {} (map vector %1 %2))

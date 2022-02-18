(ns dojo.problems-in-4clojure.059-juxtaposition)
;; https://4clojure.oxal.org/#/problem/59

(defn sol
  [& fs]
  (fn [& args]
    (map #(apply % args) fs)))

(= [21 6 1] ((sol + max min) 2 3 5 1 6 4))
(= ["HELLO" 5] ((sol #(.toUpperCase %) count) "hello"))
(= [2 6 4] ((sol :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10}))

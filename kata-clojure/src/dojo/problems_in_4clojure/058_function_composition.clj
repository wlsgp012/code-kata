(ns dojo.problems-in-4clojure.058-function-composition)
;; https://4clojure.oxal.org/#/problem/58

(defn sol
  [& fs]
  (let [[y & ys] (reverse fs)]
    (fn [& args]
      (reduce (fn [a b] (b a))
             (if (empty? (rest args)) (y (first args)) (apply y args))
             ys))))

(= [3 2 1] ((sol rest reverse) [1 2 3 4]))
(= 5 ((sol (partial + 3) second) [1 2 3 4]))
(= true ((sol zero? #(mod % 8) +) 3 5 7 9))
(= "HELLO" ((sol #(.toUpperCase %) #(apply str %) take) 5 "hello world"))

;; others
(fn [& fs] (reduce (fn [f g] #(f (apply g %&))) fs))

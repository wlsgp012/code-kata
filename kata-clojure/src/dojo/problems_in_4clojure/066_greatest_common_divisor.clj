(ns dojo.problems-in-4clojure.066-greatest-common-divisor)
;; https://4clojure.oxal.org/#/problem/66

(defn sol2
  [x y]
  (letfn [(divisors [z] (set (filter #(int? (/ z %)) (take z (rest (range))))))]
    (apply max (clojure.set/intersection (divisors x) (divisors y)))))

;; Euclidean Algorithm
(defn sol
  [x y]
  (let [r (mod x y)]
    (if (zero? r)
      y
      (recur y r))))

(= (sol 2 4) 2)
(= (sol 10 5) 5)
(= (sol 5 7) 1)
(= (sol 1023 858) 33)

;; others

(fn gcd [x y]
  (if (zero? x)
    y
    (gcd (mod y x) x)))

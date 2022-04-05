(ns dojo.problems-in-4clojure.100-least-common-multiple
  (:require [dojo.problems-in-4clojure.067-prime-numbers :refer [factors-m prime?]]))
;; https://4clojure.oxal.org/#/problem/100

(defn prime-factors
  [x]
  (filter prime? (range 1 (inc x))))

(defn separate-fraction
  [x]
  (map #(Integer/parseInt %) (clojure.string/split (str x) #"/")))

(defn divide-possible
  [a x]
  (loop [extra a r 0]
    (if (zero? (mod extra x))
      (recur (quot extra x) (inc r))
      {x r}))
  )


(defn for-denominator [m]
  (reduce (fn [r [k v]] (conj r [k (* -1 v)]))
          {}
          m))

(defn divide-by-primes
  [x]
  (if (int? x)
    (reduce (fn [r a] (merge r (divide-possible x a)))
           {1 1}
           (prime-factors x))
    (let [[numerator denominator] (separate-fraction x)
           numerator-result (divide-by-primes numerator)
           denominator-result (for-denominator (divide-by-primes denominator))]
      (merge-with max numerator-result denominator-result))))


(defn sol [& xs]
  (reduce (fn [r [n i]] (* r (int (Math/pow n i))))
          1
          (reduce (fn [m a] (merge-with max m (divide-by-primes a)))
                  {}
                  xs)))

(defn tm [& xs]
  (reduce (fn [m a] (merge-with plus-ex m (divide-by-primes a)))
          {}
          xs))

(== (sol 2 3) 6)

(== (sol 5 3 7) 105)

(== (sol 1/3 2/5) 2)

(== (sol 3/4 1/6) 3/2)

(== (sol 7 5/7 2 3/5) 210)

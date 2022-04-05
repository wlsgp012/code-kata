(ns dojo.problems-in-4clojure.100-least-common-multiple
  (:require [dojo.problems-in-4clojure.067-prime-numbers :refer [factors-m prime?]]))
;; https://4clojure.oxal.org/#/problem/100

(defn prime-factors
  [x mx]
  (println "mx=" mx)
  (filter prime? (range 1 (inc mx))))

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
  [x mx]
  (if (int? x)
    (reduce (fn [r a] (merge r (divide-possible x a)))
           {}
           (prime-factors x mx))
    (let [[numerator denominator] (separate-fraction x)
           numerator-result (divide-by-primes numerator numerator)
           denominator-result (for-denominator (divide-by-primes denominator mx))]
      (merge-with + numerator-result denominator-result))))

(defn mx [xs]
  (let [m (apply max xs)]
    (if (ratio? m)
      (apply max (flatten (map separate-fraction (filter ratio? xs))))
      m)))

(defn sol [& xs]
  (let [mx (mx xs)]
    (reduce (fn [r [n i]] (* r (rationalize (Math/pow n i))))
           1
           (reduce (fn [m a] (merge-with max m (divide-by-primes a mx)))
                   {}
                   xs))))

(defn tm [& xs]
  (let [mx (mx xs)]
    (reduce (fn [m a] (merge-with max m (divide-by-primes a mx)))
           {}
           xs)))

(== (sol 2 3) 6)

(== (sol 5 3 7) 105)

(== (sol 1/3 2/5) 2)

(== (sol 3/4 1/6) 3/2)

(== (sol 7 5/7 2 3/5) 210)

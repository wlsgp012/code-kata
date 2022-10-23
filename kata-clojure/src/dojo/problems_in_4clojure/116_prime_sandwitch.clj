(ns dojo.problems-in-4clojure.116-prime-sandwitch
  (:require [dojo.problems-in-4clojure.067-prime-numbers :refer [prime?]]))

(defn sol [n]
  (and (not= n 2)
       (prime? n)
       (let [primes (filter prime? (nnext (range)))
             serial-primes (map #(vector %1 %2 %3) primes (next primes) (nnext primes))
             [pre _ nex] (first (filter #(= n (second %)) serial-primes))]
         (= (* n 2) (+ pre nex)))))

(= false (sol 4))
(= true (sol 563))
(= 1103 (nth (filter sol (range)) 15))

;; others
(fn balanced-prime? [n]
  (letfn [(prime? [x] (if (< x 2)
                        false
                        (loop [i 2]
                          (cond
                            (< x (* i i)) true
                            (= 0 (mod x i)) false
                            :else (recur (inc i))))))]
    (if (prime? n)
      (loop [pair [(dec n) (inc n)]]
        (cond
          (every? prime? pair) true
          (not-any? prime? pair) (recur (map #(%1 %2) [dec inc] pair))
          :else false))
      false)))

(fn [n]
  (let [prime? (fn [n] (if (>= 1 n) false (->> (range 2 (inc (Math/sqrt n))) (drop-while #(pos? (mod n %))) empty?)))
        uprime (fn [n] (->> (iterate inc (inc n)) (drop-while #(not (prime? %))) first))
        lprime (fn [n] (->> (iterate dec (dec n)) (drop-while #(not (prime? %))) first))]
    (and (prime? n)
         (prime? (- (* 2 n) (uprime n)))
         (prime? (- (* 2 n) (lprime n))))))

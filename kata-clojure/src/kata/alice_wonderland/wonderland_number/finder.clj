(ns kata.alice-wonderland.wonderland-number.finder)
;; https://github.com/gigasquid/wonderland-clojure-katas/tree/master/wonderland-number

(defn- to-digit-set [n]
  (set (str n)))

(defn- hasAllTheSameDigits? [a b]
  (= (to-digit-set a) (to-digit-set b)))

(defn- all-match? [n]
  (and
   (hasAllTheSameDigits? n (* 2 n))
   (hasAllTheSameDigits? n (* 3 n))
   (hasAllTheSameDigits? n (* 4 n))
   (hasAllTheSameDigits? n (* 5 n))
   (hasAllTheSameDigits? n (* 6 n))))

(defn wonderland-number []
  (first (filter all-match? (range 100000 1000000))))

;; others
(defn brute []
  "Brute force solution to produce a sequence of six-digit numbers
  whose products of 2, 3, 4, 5, and 6 all contain the same digits
  as the number itself."
  (for [n (range 100000 1000000)
        :let [n2 (set (str (* n 2)))
              n3 (set (str (* n 3)))
              n4 (set (str (* n 4)))
              n5 (set (str (* n 5)))
              n6 (set (str (* n 6)))]
        :when (= (set (str n)) n2 n3 n4 n5 n6)]
    n))

(defn wonderland-number []
  (first (for [candidate (range 100000 166667)              ; 6 * 166666 is the last 6 digit number
               :let [sorted-vals (map (comp sort str #(* candidate %)) (range 1 7))]
               :when (apply = sorted-vals)]
           candidate)))

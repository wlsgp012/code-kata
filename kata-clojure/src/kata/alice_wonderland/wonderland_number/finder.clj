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
  (first (filter all-match? (range 100000 999999))))

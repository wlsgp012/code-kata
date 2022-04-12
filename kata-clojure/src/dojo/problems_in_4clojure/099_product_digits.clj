(ns dojo.problems-in-4clojure.099-product-digits)
;; https://4clojure.oxal.org/#/problem/99

(defn sol [a b]
  (map #(Integer/parseInt (str %)) (str (* a b))))


(= (sol 1 1) [1])

(= (sol 99 9) [8 9 1])

(= (sol 999 99) [9 8 9 0 1])

;; others

(fn [a b]
  (map
   #(Integer. (str %))
   (str (* a b))))

(fn dig [a b] (map #(read-string (str %)) (str (* a b))))

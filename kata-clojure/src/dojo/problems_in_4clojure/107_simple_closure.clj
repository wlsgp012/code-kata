(ns dojo.problems-in-4clojure.107-simple-closure)
;; https://4clojure.oxal.org/

(defn sol [y] (fn [x] (int (Math/pow x y))))

(= 256 ((sol 2) 16), ((sol 8) 2))

(= [1 8 27 64] (map (sol 3) [1 2 3 4]))

(= [1 2 4 8 16] (map #((sol %) 2) [0 1 2 3 4]))

;; others
#(fn [x]
   (reduce * (repeat % x)))

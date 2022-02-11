(ns dojo.problems-in-4clojure.043-reverse-interleave)
;; https://4clojure.oxal.org/#/problem/43

(defn answer
  [coll n]
  (partition-all 2 (apply interleave (partition-all n coll))))


(= (answer [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6)))
(= (answer (range 9) 3) '((0 3 6) (1 4 7) (2 5 8)))
(= (answer (range 10) 5) '((0 5) (1 6) (2 7) (3 8) (4 9)))

;; others
(fn [cols c]
  (vals (group-by #(mod % c) cols)))

#(apply map list (partition %2 %))

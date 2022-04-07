(ns dojo.problems-in-4clojure.095-to-tree-or-not-to-tree)
;; https://4clojure.oxal.org/#/problem/95

(defn sol
  [xs]
  (if (and (= 3 (count xs))
           (every? #(or (sequential? %) (nil? %)) (rest xs)))
    (reduce #(and %1 (sol %2))
            true
            (filter sequential? xs))
    false))


(= (sol '(:a (:b nil nil) nil))
   true)

(= (sol '(:a (:b nil nil)))
   false)

(= (sol [1 nil [2 [3 nil nil] [4 nil nil]]])
   true)

(= (sol [1 [2 nil nil] [3 nil nil] [4 nil nil]])
   false)

(= (sol [1 [2 [3 [4 nil nil] nil] nil] nil])
   true)

(= (sol [1 [2 [3 [4 false nil] nil] nil] nil])
   false)

(= (sol '(:a nil ()))
   false)


;; others
(fn tree? [coll]
  (or (nil? coll)
      (and (sequential? coll)
           (= 3 (count coll))
           (tree? (nth coll 1))
           (tree? (nth coll 2)))))


(fn t [x]
  (or (nil? x)
      (and (coll? x) (= 3 (count x)) (every? t (rest x)))))

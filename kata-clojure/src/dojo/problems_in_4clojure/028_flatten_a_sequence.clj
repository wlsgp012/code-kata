(ns dojo.problems-in-4clojure.028-flatten-a-sequence)
;; https://4clojure.oxal.org/#/problem/28

(defn answer
  [col]
  (reduce
   (fn [r a]
     (into r (if (coll? a)
               (answer a)
               [a])))
   []
   col))

(= (answer '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
(= (answer ["a" ["b"] "c"]) '("a" "b" "c"))
(= (answer '((((:a))))) '(:a))

;; others
(fn flat [xs]
  (if (sequential? xs)
    (mapcat  flat xs)
    (list xs)))

(fn [x]
  (filter (complement sequential?)
          (rest (tree-seq sequential? seq x))))

(fn [coll]
  (loop [f1 coll]
    (let [f2 (apply concat (map #(if (sequential? %) % [%]) f1))]
      (if (= f1 f2) f2 (recur f2)))))

#(loop [xs % flat '()]
   (if (empty? xs)
     flat
     (let [head (first xs)]
       (if (sequential? head)
         (recur (concat head (next xs)) flat)
         (recur (next xs) (concat flat (list head)))))))

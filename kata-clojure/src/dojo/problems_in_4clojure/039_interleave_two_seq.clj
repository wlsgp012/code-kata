(ns dojo.problems-in-4clojure.039-interleave-two-seq)

(defn answer
  [xs ys]
  (loop [[a & as] xs [b & bs] ys result []]
    (if (or (nil?  a) (nil?  b))
      result
      (recur as bs (conj result  a  b)))))

(= (answer [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))
(= (answer [1 2] [3 4 5 6]) '(1 3 2 4))
(= (answer [1 2 3 4] [5]) [1 5])
(= (answer [30 20] [25 15]) [30 25 20 15])

;; others
#(apply concat (map vector %1 %2))

(fn [as bs]
  (flatten  (map #(conj [] %1 %2) as bs)))

#(mapcat vector %1 %2)

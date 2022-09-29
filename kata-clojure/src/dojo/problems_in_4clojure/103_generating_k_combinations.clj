(ns dojo.problems-in-4clojure.103-generating-k-combinations)

(defn helper [n s c result]
  (if (= n c)
    result
    (recur n s (inc c) (set (filter #(= (count %) (inc c)) (for [x s y result] (conj y x)))))))

(defn sol [n s]
  (if (> n (count s))
    (hash-set)
    (helper n s 1 (set (map hash-set s)))))

(= (sol 1 #{4 5 6}) #{#{4} #{5} #{6}})

(= (sol 10 #{4 5 6}) #{})

(= (sol 2 #{0 1 2}) #{#{0 1} #{0 2} #{1 2}})

(= (sol 3 #{0 1 2 3 4}) #{#{0 1 2} #{0 1 3} #{0 1 4} #{0 2 3} #{0 2 4}
                         #{0 3 4} #{1 2 3} #{1 2 4} #{1 3 4} #{2 3 4}})

(= (sol 4 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a "abc" "efg"}})

(= (sol 2 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a} #{[1 2 3] "abc"} #{[1 2 3] "efg"}
                                      #{:a "abc"} #{:a "efg"} #{"abc" "efg"}})

;; others
(fn C [k s]
  (cond
    (zero? k) #{#{}}
    (empty? s) #{}
    :else (set (clojure.set/union
                (map #(conj % (first s)) (C (dec k) (rest s)))
                (C k (rest s))))))

(fn [K S]
  (letfn [(kcom [k s]
            (cond (< (count s) k) #{}
                  (= 1 k) (apply hash-set (for [e s] #{e}))
                  :else
                  (apply clojure.set/union
                         (for [e s]   (apply hash-set (map #(clojure.set/union % #{e}) (kcom (dec k) (disj s e))))))))]
    (kcom K S)))

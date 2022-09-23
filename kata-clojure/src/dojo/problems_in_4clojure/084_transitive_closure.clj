(ns dojo.problems-in-4clojure.084-transitive-closure)
;; https://4clojure.oxal.org/#/problem/84

(defn sol
  [ss]
  (letfn [(find-tails [group [f l] r]
            (let [next (first (filter #(= l (first %)) group))]
              (if next (recur group [f (last next)] (conj r [f (last next)])) r)))]
    (reduce (fn [r e] (into r (find-tails r e [e])))
           ss
           ss)))

(let [divides #{[8 4] [9 3] [4 2] [27 9]}]
  (= (sol divides) #{[4 2] [8 4] [8 2] [9 3] [27 9] [27 3]}))

(let [more-legs
      #{["cat" "man"] ["man" "snake"] ["spider" "cat"]}]
  (= (sol more-legs)
     #{["cat" "man"] ["cat" "snake"] ["man" "snake"]
       ["spider" "cat"] ["spider" "man"] ["spider" "snake"]}))

(let [progeny
      #{["father" "son"] ["uncle" "cousin"] ["son" "grandson"]}]
  (= (sol progeny)
     #{["father" "son"] ["father" "grandson"]
       ["uncle" "cousin"] ["son" "grandson"]}))

;; others
#(let [k (into % (for [[a b] % [c d] % :when (= b c)] [a d]))]
   (if (= k %) % (recur k)))

(fn [r]
  (->>
   r
   (iterate #(into % (for [[x y] % [y1 z] % :when (= y y1)] [x z])))
   (partition 2)
   (filter #(apply = %))
   first first))


#(loop [coll %]
   (let [x (set (concat coll (for [[a b] coll [c d] coll :when (= b c)] [a d])))]
     (if (= x coll)
       x
       (recur x))))

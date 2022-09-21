(ns dojo.problems-in-4clojure.084-transitive-closure)
;; https://4clojure.oxal.org/#/problem/84

(defn sol2
  [ss]
  (reduce (fn [r x]
            (if (= (last (last r)) (first x))
              (conj (vec (drop-last r)) (conj (vec (last r)) (last x)))
              (conj r x)))
          []
          (sort (comp - compare) ss)))

(defn sol3
  [ss]
  (letfn [(neighbor [[ak av]]
            (first (filter seq (map
                          (fn [[bk bv]]
                            (cond
                              (= ak bv) [bk av]
                              (= av bk) [ak bv]
                              :else nil))  ss))))]
    (reduce (fn [r x]
              (conj r x (neighbor x)))
            #{}
            ss)))

(defn sol4
  [ss]
  (let [keymap (into {} ss)
        valuemap (into {} (map (fn [[a b]] [b a] ss)))
        group (reduce (fn [r [k v]] ))]))

(defn sol
  [ss]
  (letfn [(find-tails [group [f l] r]
            (let [next (first (filter #(= l (first %)) group))]
              (if next (recur group next (conj r next)) r)))]
    (reduce (fn [r e] (find-tails group e []))
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

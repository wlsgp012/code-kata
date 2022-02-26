(ns dojo.problems-in-4clojure.069-merge-with-a-function)
;; https://4clojure.oxal.org/#/problem/69

(defn sol
  [f m & ms]
  (reduce (fn [rm [k v]] (assoc rm k (if (contains? rm k)
                                       (apply f (cons (rm k) (map second v)))
                                       (first (map second v)))))
          m
          (group-by first (reduce #(concat %1 (seq %2)) [] ms))))

(= (sol * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})
   {:a 4, :b 6, :c 20})
(= (sol - {1 10, 2 20} {1 3, 2 10, 3 15})
   {1 7, 2 10, 3 15})
(= (sol concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]})
   {:a [3 4 5], :b [6 7], :c [8 9]})

;; merge-with source
(defn merge-with
  "Returns a map that consists of the rest of the maps conj-ed onto
  the first.  If a key occurs in more than one map, the mapping(s)
  from the latter (left-to-right) will be combined with the mapping in
  the result by calling (f val-in-result val-in-latter)."
  {:added "1.0"
   :static true}
  [f & maps]
  (when (some identity maps)
    (let [merge-entry (fn [m e]
			(let [k (key e) v (val e)]
			  (if (contains? m k)
			    (assoc m k (f (get m k) v))
			    (assoc m k v))))
          merge2 (fn [m1 m2]
		   (reduce merge-entry (or m1 {}) (seq m2)))]
      (reduce merge2 maps))))

;; others
(fn[f m & ms]
  (reduce
   (fn[m n](reduce
            (fn[x [k v]](assoc x k (if(x k)(f(x k)v)v)))
            mn
            n))
   m
   ms))

(fn [f & a] (reduce #(reduce (fn [r [k v]] (assoc r k (if (r k) (f (r k) v) v))) % %2) a))

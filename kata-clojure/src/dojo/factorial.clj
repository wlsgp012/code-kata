(ns dojo.factorial)

(defn factorial [n]
  (case n
    0 1
    (* (bigint n) (factorial (dec n)))))

(defn factorial-tail
  ([n] (factorial-tail (bigint n) 1))
  ([n acc]
   (case n
     0 acc
     (recur (dec n) (* n acc)))))

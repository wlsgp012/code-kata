(ns dojo.problems-in-4clojure.150-palindromic-number)

;; slow
(defn sol2 [x]
  (let [as-string (str x)
        palindrome? (= (list* as-string) (reverse as-string))
        n (lazy-seq (sol2 (inc x)))]
    (if palindrome?
      (cons x n)
      n)))

;; also slow
(defn sol [x]
  (let [palindrome? (fn c [xs] (if (seq xs)
                                 (and (= (first xs) (last xs)) (c (drop-last (rest xs))))
                                 true))
        n (lazy-seq (sol (inc x)))]
    (if (palindrome? (str x))
      (cons x n)
      n)))

(comment (= (take 26 (sol 0))
            [0 1 2 3 4 5 6 7 8 9
             11 22 33 44 55 66 77 88 99
             101 111 121 131 141 151 161])

         (= (take 16 (sol 162))
            [171 181 191 202
             212 222 232 242
             252 262 272 282
             292 303 313 323])

         (= (take 6 (sol 1234550000))
            [1234554321 1234664321 1234774321
             1234884321 1234994321 1235005321])

         (= (first (sol (* 111111111 111111111)))
            (* 111111111 111111111))

         (= (set (take 199 (sol 0)))
            (set (map #(first (sol %)) (range 0 10000))))

         (= true
            (apply < (take 6666 (sol 9999999))))

         (= (nth (sol 0) 10101)
            9102019))

;; others

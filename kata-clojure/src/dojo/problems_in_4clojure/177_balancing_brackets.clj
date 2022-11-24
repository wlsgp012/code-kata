(ns dojo.problems-in-4clojure.177-balancing-brackets)

(defn sol [s]
  (let [d {")" "(", "]" "[", "}" "{"}]
    (empty?
     (reduce (fn [stack x] (if-let [open (d x)]
                         (if (= (peek stack) open) (pop stack) (conj stack "x"))
                         (conj stack x)))
             []
             (re-seq #"[(){}\[\]]" s)))))

(sol "This string has no brackets.")

(sol "class Test {
                          public static void main(String[] args) {
                            System.out.println(\"Hello world.\");
                          }
                        }")

(not (sol "(start, end]"))

(not (sol "())"))

(not (sol "[ { ] } "))

(sol "([]([(()){()}(()(()))(([[]]({}()))())]((((()()))))))")

(not (sol "([]([(()){()}(()(()))(([[]]({}([)))())]((((()()))))))"))

(not (sol "["))

;; others
#(= ()
    (reduce
     (fn [a x]
       (if a (case x
               (\[ \{ \() (conj a x)
               (\] \} \))
               (if (= ({\] \[ \} \{ \) \(} x) (first a))
                 (rest a) nil)
               a)))
     () %))

#(empty?
  (reduce (fn [[h & t :as st] ch]
            (cond
              (= ({\{ \} \( \) \[ \]} h) ch) t
              (#{\{ \} \( \) \[ \]} ch) (cons ch st)
              :else st))
          ()
          %))

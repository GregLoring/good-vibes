(ns good-vibes.core-test
  (:require [clojure.test :refer :all]
            [good-vibes.core :refer [msg]]))

(deftest msg-test
  (testing "The message should only have a comma when name is given (truthy)."
    (is (= (msg) "Hello!"))
    (is (= (msg nil) "Hello!"))
    (is (= (msg false) "Hello!"))
    (is (= (msg "World") "Hello, World!"))))

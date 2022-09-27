# VS Code setup for Clojure programming

The basic idea is to fork, clone, or download this repo so you can
start working your way through
[The Brave and True](https://www.braveclojure.com/clojure-for-the-brave-and-true/)
without spending a thousand years setting up emacs.

Quick Start:

- Do the [Minimal Environment Setup](#minimal-environment-setup)
- Open the `src/good_vibes/core.clj` file in the Editor
- Type `ctrl+alt+c ctrl+alt+j` to start the REPL (choose the `Leiningen` project type and the `:uberjar` launch profile)
- Enter `(-main)` in the REPL to see "Hello, World!"
- Start hacking away in the REPL tab
- Read [Thinking in Calva Keyboard Shortcut Combos](#thinking-in-calva-keyboard-shortcut-combos)
- Check out [Tidbits and Secret Sauce](#tidbits-and-secret-sauce) sometime

I also have plans to add some additional material to

- help you write a test for a "Brave and True" exercise
- introduce you to some fun libraries, like `plumatic/schema` and `metosin/compojure-api`

## Minimal Environment Setup

### Use [SDKMAN!](https://sdkman.io/) for java and leiningen.

```bash
sdk install java
sdk install leiningen
```

Note: Leiningen gives you Clojure

### Install VS Code with these Extensions:

- Calva
- macros (by geddski)

Check that `lein` is on VS Code's path. "I had to do this: <https://code.visualstudio.com/docs/setup/mac> and then start VS Code by using `code` command." - NickM

## Thinking in Calva Keyboard Shortcut Combos

`ctrl+shift+p` is the VS Code keybinding for **Show All Commands**. Therefore, a lot of docs have you start there so that you can find the command they want you to use.

The Calva commands are often combos of two keystrokes, where the first keystroke of the combo is `ctrl+alt+c`.  So, I tend to think of these combos as `ctrl+alt+c` **Calva** followed by `keybinding` *Calva command*.

For example, `ctrl+alt+c ctrl+alt+j` is the hot-key combo for **Start a Project REPL and Connect**.  Putting it all together, my brain is thinking `ctrl-alt-c` **Calva** `ctrl-alt-j` **Jack-in**

[The Top 10 Calva Commands](https://calva.io/commands-top10/) according to `calva.io`.

## Tidbits and Secret Sauce

### Slurp and Barf

Learn about `Slurp Forward` and `Barf Forward` in the Editing section
of [Paredit, a Visual Guide](https://calva.io/paredit/).
They are by far the most helpful Paredit commands.

### Some helpful settings and macros (The Secret Sauce)

Calva specific workspace settings are in `.vscode/settings.json`.  Also consider `calva.replConnectSequences`.

`calvaRebuild` SHOULD do everything necessary to update all of the namespaces in the REPL,
but I'm not sure it actually does. You may need to do this twice because file order is not deterministic
and if you change a function name or something, dependent files may fail the first time.

Add the keyboard shortcut bindings from `clj-keybindings` to `keybindings.json`
via `ctrl+shift+p` > **Preferences: Open Keyboard Shortcuts (JSON)**

Note: It would be nice to have a `.vscode/keybindings.json` file,
but this [VSCode issue](https://github.com/Microsoft/vscode/issues/4504) explains why it's not supported,
and suggests making an extension (which doesn't look so simple to me!).

Think of `ctrl+alt+c ctrl+alt+k` as “kill REPL”.

### prettifier (formatter)

In project.clj,

```clojure
 :plugins [[lein-cljfmt "0.6.4"] ...
```

```bash
$ git ls-files -mo | egrep -v '^target/' | egrep '.clj$'
… returns new and modified clojure files. So,
$ git ls-files -mo | egrep -v '^target/' | egrep '.clj$' | xargs lein cljfmt check
```

### user profiles

~/.lein/profiles.clj

```clojure
{:oscar {:jvm-opts ["-DDB_HOST=…"]}}
```

### linter

.clj-kondo/config.edn

```clojure
{:linters {:refer-all {:level :off}}}
```

Update .gitignore to suppress files create by linter: `.clj-kondo/.cache/*/lock`.

Update .gitignore to suppress files created by Calva “Run … tests” commands: `*.transit.json`.

## Alternatives

Clojure environment options that didn't get chosen (obviously).

emacs, vim, tmux, ...

[Gorilla-REPL](http://gorilla-repl.org/) is an alternative "in the notebook style.  You can think of it like a pretty REPL that can plot graphs and draw tables, or you can think of it as an editor for rich documents that can contain interactive Clojure code, graphs, tables, notes, LaTeX formulae."

defmodule Puzzle01Test do
  use ExUnit.Case

  test "Lines" do
    assert Puzzle01.lines(2) == [" #", "##"]
  end

  test "Smallest staircase" do
    assert Puzzle01.solve(1) == "#"
  end

  test "Larger staircases" do
    assert Puzzle01.solve(2) == " #\n##"
    assert Puzzle01.solve(3) == "  #\n ##\n###"
  end

  test "Unnatural staircase" do
    assert Puzzle01.solve(-1) == ""
  end
end

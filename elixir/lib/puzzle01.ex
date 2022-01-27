defmodule Puzzle01 do
  @moduledoc """
  See [Staircase | Hackerrank](https://www.hackerrank.com/challenges/staircase/problem?isFullScreen=true)
  """

  def lines(1) do ["#"] end

  def lines(n) when n > 1 do
    upper = for line <- lines(n - 1), do: " " <> line
    upper ++ [String.duplicate("#", n)]
  end

  def solve(n) when n > 0 do
    Enum.join(lines(n), "\n")
  end

  def solve(n) do "" end
end

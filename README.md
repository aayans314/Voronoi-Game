# Voronoi Game Strategic AI

Advanced algorithms for competitive resource allocation in graph-based Voronoi games, featuring weighted heuristics and strategic opponent blocking capabilities.

## Overview

This project develops intelligent algorithms for the Voronoi Game, a competitive graph-based strategy game modeling real-world resource allocation scenarios like store placement in competitive markets. Through extensive testing (2000+ games), the algorithms demonstrate superior performance over baseline approaches using sophisticated vertex evaluation and strategic blocking techniques.

## Game Context

In the Voronoi Game, players compete to control districts (graph vertices) with associated values. Each vertex influences nearby districts based on distance, creating territorial control zones. Players alternate turns selecting vertices to maximize their total controlled value.

## Algorithm Implementations

### VoronoiMaxAlgorithm (Primary Strategy)

A comprehensive vertex evaluation algorithm using three weighted factors:

#### Core Evaluation Factors

1. **Value Factor**
   - Base vertex value multiplied by 2 for emphasis
   - Late-game amplification for direct value prioritization
   - Adaptive weighting based on remaining turns

2. **Edge Factor**
   - Evaluates influence on neighboring vertices
   - Formula: `neighbor_value / sqrt(distance)`
   - Inspired by inverse square law physics with optimized exponent (0.5)

3. **Left Neighbors**
   - Counts winnable adjacent territories
   - Weighted by 50× (average vertex value)
   - Excludes opponent-controlled vertices

#### Scoring Formula
```
vertex_score = (2 × value) + edge_factor + (50 × left_neighbors)
```

### VoronoiBlockerAlgorithm (Defensive Strategy)

Strategic opponent disruption through proximity-based blocking:

#### Evaluation Factors

1. **Value Factor**
   - Direct vertex value with late-game doubling
   - Prioritizes high-value territory acquisition

2. **Blocking Score**
   - Proximity to opponent-controlled vertices
   - Formula: `opponent_neighbor_value / distance`
   - Disrupts opponent expansion patterns

## Performance Results

### VoronoiMaxAlgorithm Performance
Tested across 2000 games each:

| Opponent | Wins | Losses | Win Rate |
|----------|------|--------|----------|
| Random Algorithm | 1939 | 61 | **96.95%** |
| Greedy Algorithm | 1639 | 360 | **82.0%** |

### VoronoiBlockerAlgorithm Performance

| Opponent | Wins | Losses | Win Rate |
|----------|------|--------|----------|
| Random Algorithm | 1685 | 314 | **84.25%** |
| Greedy Algorithm | 1029 | 970 | **51.45%** |

## Technical Analysis

### Time Complexity

**VoronoiMaxAlgorithm**: O(V²)
- Double loop over vertices with constant-time operations
- Distance calculations: O(V³) preprocessing, O(1) lookup via HashMap

**VoronoiBlockerAlgorithm**: O(V × N)
- V vertices × N average neighbors per vertex
- More efficient for sparse graphs

### Algorithmic Insights

1. **Weighted Optimization**: Careful parameter tuning significantly improves performance
2. **Physics-Inspired Heuristics**: Square root distance weighting outperforms linear and quadratic alternatives
3. **Game Phase Adaptation**: Late-game value amplification prevents suboptimal endgame moves
4. **Strategic Blocking**: Proximity-based opponent disruption creates competitive advantage

## Key Innovations

### Multi-Factor Vertex Evaluation
- **Holistic Scoring**: Combines direct value, territorial influence, and expansion potential
- **Dynamic Weighting**: Adapts strategy based on game state and remaining turns
- **Neighbor Analysis**: Considers both immediate and potential future control

### Strategic Adaptations
- **Early Game**: Emphasizes territorial expansion and influence zones
- **Late Game**: Prioritizes high-value direct acquisitions
- **Opponent Awareness**: Blocking algorithm disrupts opponent expansion strategies

## Algorithm Design Philosophy

### VoronoiMaxAlgorithm
- **Maximalist Approach**: Seeks to maximize total controlled value
- **Influence-Based**: Considers indirect control through proximity
- **Adaptive Strategy**: Adjusts priorities based on game progression

### VoronoiBlockerAlgorithm  
- **Defensive Strategy**: Focuses on disrupting opponent expansion
- **Proximity Warfare**: Targets opponent-adjacent territories
- **Contested Zone Control**: Prioritizes winning disputed areas

## Research Contributions

1. **Heuristic Optimization**: Demonstrated effectiveness of weighted multi-factor evaluation
2. **Physics-Inspired Computing**: Successfully applied inverse square law concepts to graph algorithms
3. **Game Theory Implementation**: Practical algorithms for competitive resource allocation
4. **Performance Validation**: Extensive empirical testing with statistical significance

## Technical Highlights

- **Graph Theory Application**: Efficient shortest-path calculations with HashMap optimization
- **Heuristic Design**: Multi-factor scoring with empirically tuned weights
- **Performance Analysis**: Comprehensive testing across diverse scenarios
- **Strategic AI**: Opponent-aware decision making with blocking capabilities

## Author

**Aayan Shah**  
Computer Science & Physics Student  
[GitHub Profile](https://github.com/aayans314)
